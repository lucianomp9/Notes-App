import React from "react";
import Note from "../../Note";

export default function NotesList({ notes, deleteNotes }) {
  return (
    <>
      <div className="notes-list">
        {notes.map((note) => (
          <Note key={note.uuid} note={note} deleteNotes={deleteNotes} />
        ))}
      </div>
    </>
  );
}
