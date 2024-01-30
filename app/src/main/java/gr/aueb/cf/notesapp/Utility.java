package gr.aueb.cf.notesapp;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.text.SimpleDateFormat;
/**
 * Utility class containing helper methods for common functionalities.
 */
public class Utility {

    /**
     * Displays a short Toast message.
     *
     * @param context The context in which the Toast will be displayed.
     * @param message The message to be displayed in the Toast.
     */
    static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    /**
     * Retrieves the Firestore collection reference for user's notes.
     *
     * @return CollectionReference for user's notes in Firestore.
     */
    static CollectionReference getCollectionReferenceForNotes(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("notes")
                .document(currentUser.getUid()).collection("my_notes");
    }

    /**
     * Converts a Timestamp to a formatted string (dd/MM/yyyy).
     *
     * @param timestamp The Timestamp to be converted.
     * @return A formatted string representing the Timestamp.
     */
    static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("dd/MM/yyyy").format(timestamp.toDate());
    }

}