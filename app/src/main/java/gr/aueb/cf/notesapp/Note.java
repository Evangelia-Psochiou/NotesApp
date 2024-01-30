package gr.aueb.cf.notesapp;

import com.google.firebase.Timestamp;

/**
 * Model class representing a Note with a title, content, and timestamp.
 */
public class Note {

    // Fields
    String title;
    String content;
    Timestamp timestamp;

    /**
     * Default constructor required for Firebase.
     */
    public Note() {
    }

    /**
     * Gets the title of the note.
     *
     * @return The title of the note.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the note.
     *
     * @param title The title to be set for the note.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the note.
     *
     * @return The content of the note.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the note.
     *
     * @param content The content to be set for the note.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp of the note creation.
     *
     * @return The timestamp of the note creation.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the note creation.
     *
     * @param timestamp The timestamp to be set for the note creation.
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
