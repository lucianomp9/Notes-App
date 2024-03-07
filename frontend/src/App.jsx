import * as React from "react";
import "./App.css";
import { useState, useEffect } from "react";
import axios from "axios";
import CreateNote from "./Components/CreateNote/CreateNote";
import NotesList from "./Components/NoteList/NotesList";
import FilterNote from "./Components/FilterNote/FilterNote";

const apiUrl = "http://localhost:8080/api/v1/";

function App() {
  const [notes, setNotes] = useState([]);
  const [categories, setCategories] = useState([]);
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

    // FETCH CATEGORIES
    axios
      .get(apiUrl + "category")
      .then(function (response) {
        const newData = response.data.map((category) => ({
          value: category.id,
          label: category.name,
        }));
        setCategories(newData);
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

  const updateNote = (updatedNote) => {
    setNotes((prevNotes) => {
      return prevNotes.map((note) => {
        if (note.uuid === updatedNote.uuid) {
          return {
            ...note,
            archived:
              updatedNote.archived !== undefined
                ? updatedNote.archived
                : note.archived,
            status:
              updatedNote.status !== undefined
                ? updatedNote.status
                : note.status,
            title:
              updatedNote.title !== undefined ? updatedNote.title : note.title,
            description:
              updatedNote.description !== undefined
                ? updatedNote.description
                : note.description,
          };
        }
        return note;
      });
    });
  };

  const deleteNotes = (noteToDelete) => {
    setNotes((prevNotes) => {
      const updatedNotes = prevNotes.filter(
        (note) => note.uuid !== noteToDelete.uuid
      );
      return updatedNotes;
    });
  };

  const updateCategories = (newCategory) => {
    const addCategory = {
      value: newCategory.value,
      label: newCategory.label,
    };
    setCategories((prevCategories) => [addCategory, ...prevCategories]);
  };

  const deleteCategoryFromList = (deletedCategoryId) => {
    // Filters out sent category from list
    setCategories((prevCategories) => {
      const updatedCategories = prevCategories.filter(c => c.value === deletedCategoryId);
      return updatedCategories
    })
  }


  return (
    <>
      <CreateNote
        updateNotes={updateNotes}
        updateCategories={updateCategories}
        categories={categories}
        deleteCategoryFromList={deleteCategoryFromList}
      />
      <FilterNote filterValueSelected={onFilterValueSelected} />
      <NotesList
        notes={filteredNoteList}
        deleteNotes={deleteNotes}
        updateNote={updateNote}
      />
    </>
  );
}

export default App;
