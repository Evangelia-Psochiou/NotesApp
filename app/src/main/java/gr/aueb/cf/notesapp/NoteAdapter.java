package gr.aueb.cf.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

/**
 * Adapter class for displaying Note items in a RecyclerView.
 */
public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {

    Context context;

    /**
     * Constructor for the NoteAdapter.
     *
     * @param options FirestoreRecyclerOptions containing the data to be displayed.
     * @param context The context of the calling activity.
     */
    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    /**
     * Binds data to the views in the ViewHolder.
     *
     * @param holder   The ViewHolder containing the views.
     * @param position The position of the item in the dataset.
     * @param note     The Note object to be displayed.
     */
    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        holder.titleTextView.setText(note.title);
        holder.contentTextView.setText(note.content);
        holder.timestampTextView.setText(Utility.timestampToString(note.timestamp));

        // Set click listener to navigate to NoteDetailsActivity
        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, NoteDetailsActivity.class);
            intent.putExtra("title", note.title);
            intent.putExtra("content", note.content);

            // Retrieve the document ID of the clicked note
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);

            context.startActivity(intent);
        } );

    }

    /**
     * Creates new ViewHolders when needed.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The type of the view.
     * @return A new NoteViewHolder instance.
     */
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent,false);
        return new NoteViewHolder(view);
    }

    /**
     * ViewHolder class to hold the views for each Note item.
     */
    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, timestampTextView;

        /**
         * Constructor for the NoteViewHolder.
         *
         * @param itemView The View containing the layout for each Note item.
         */
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.note_title_text_view);
            titleTextView = itemView.findViewById(R.id.note_content_text_view);
            titleTextView = itemView.findViewById(R.id.note_timestamp_text_view);
        }
    }
}
