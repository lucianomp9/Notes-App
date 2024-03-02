import * as React from "react";
import "./App.css";
import { useState, useEffect } from "react";
import axios from "axios"; // Import axios
import CreateNote from "./Components/CreateNote/CreateNote";
import NotesList from "./Components/NoteList/NotesList";
import FilterNote from "./Components/FilterNote/FilterNote";

const apiUrl = "http://localhost:8080/api/v1/";

function App() {
  const [notes, setNotes] = useState([]);
  const [filterTextValue, setFilterTextValue] = useState("all");

  const filteredNoteList = notes.filter((note) => {
    if (filterTextValue === "archived") {
      return note.archived === true;
    } else if (filterTextValue === "unarchived") {
      return note.archived === false;
    } else if (filterTextValue === "completed") {
      return note.status === true;
    } else if (filterTextValue === "pending") {
      return note.status === false;
    } else {
      return note;
    }
  });

  useEffect(() => {
    axios
      .get(apiUrl + "note")
      .then(function (response) {
        const data = response.data.map((note) => ({
          uuid: note.uuid,
          title: note.title,
          description: note.description,
          category: note.category,
          status: note.status,
          archived: note.archived,
        }));
        setNotes(data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  const onFilterValueSelected = (filterValue) => {
    setFilterTextValue(filterValue);
  };

  const updateNotes = (newNote) => {
    setNotes((prevNotes) => [newNote, ...prevNotes]);
  };

  const deleteNotes = (noteToDelete) => {
    setNotes((prevNotes) => {
      const updatedNotes = prevNotes.filter(
        (note) => note.uuid !== noteToDelete.uuid
      );
      return updatedNotes;
    });
  };

  return (
    <>
      <CreateNote updateNotes={updateNotes} />
      <FilterNote filterValueSelected={onFilterValueSelected} />
      <NotesList notes={filteredNoteList} deleteNotes={deleteNotes} />
    </>
  );
}

export default App;
