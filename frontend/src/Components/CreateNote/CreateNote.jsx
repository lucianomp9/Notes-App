import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  TextField,
  Button,
  FormControl,
  Box,
  Modal,
  Toolbar,
  AppBar,
  Typography,
  Alert,
  Snackbar,
} from "@mui/material";
import CreatableSelect from "react-select/creatable";

const apiUrl = "http://localhost:8080/api/v1/";

export default function MyComponent({
  updateNotes,
  updateCategories,
  categories,
  deleteCategoryFromList
}) {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [category, setCategory] = useState({});
  const [openCreateModal, setOpenCreateModal] = useState(false);
  const [openDeleteCategoryModal, setOpenDeleteCategoryModal] = useState(false);
  const [errorAlert, setErrorAlert] = useState({
    open: false,
    message: "",
  });

  //Create category
  const createCategory = (category) => {
    axios
      .post(`${apiUrl}category`, category)
      .then(function (response) {
        const newCategory = {
          value: response.data.id,
          label: response.data.name,
        };
        // Set created category as selected one
        setCategory(newCategory);
        // Updates categories list
        updateCategories(newCategory);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  //Handle selected category
  const handleChange = (selectedOption) => {
    setCategory(selectedOption);
  };

  //Create note
  const handleCreate = () => {
    setOpenCreateModal(true);
  };

  const handleDeleteCategory = () => {
    setOpenDeleteCategoryModal(true);
  };
  const handleClose = () => {
    setOpenCreateModal(false);
    setOpenDeleteCategoryModal(false);
  };

  const postNote = () => {
    axios
      .post(apiUrl + "note", {
        title: title,
        description: description,
        category: {
          id: category.value,
          name: category.label,
        },
      })
      .then(function (response) {
        updateNotes(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const saveBtn = (e) => {
    e.preventDefault();
    postNote();
    //Refresh inputs
    setTitle("");
    setDescription("");
    setCategory();
    handleClose();
  };

  //Delete category
  const deleteCategory = () => {
    axios
      .delete(`${apiUrl}category/${category.value}`)
      .then(function (response) {
        handleClose();
        deleteCategoryFromList(response.data.value)
        setCategory()
      })
      .catch(function (error) {
        setErrorAlert({
          open: true,
          message: `Error deleting category. There are notes with this category. Please delete them before removing the "${category.label}" category.`,
        });
        console.log(error);
      });
  };

  return (
    <>
      <Box
        sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}
      >
        <AppBar position="fixed" sx={{ top: 0 }}>
          <Toolbar>
            <Typography variant="h5" component="div" sx={{ flexGrow: 1 }}>
              📝Notes SPA
            </Typography>
            <Button
              onClick={handleCreate}
              variant="contained"
              color="success"
              sx={{ position: "fixed", right: 15 }}
            >
              Create Note
            </Button>
            <Button
              onClick={handleDeleteCategory}
              variant="contained"
              color="warning"
              sx={{ position: "fixed", right: 150 }}
            >
              Delete Category
            </Button>
          </Toolbar>
        </AppBar>
      </Box>

      <Modal
        open={openCreateModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box id="editModal" sx={{ color: "black" }}>
          <TextField
            id="outlined-basic"
            label="Note title"
            variant="outlined"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <TextField
            id="outlined-basic"
            label="Note description"
            variant="outlined"
            value={description}
            sx={{ mt: 3 }}
            onChange={(e) => setDescription(e.target.value)}
          />
          <FormControl sx={{ mt: 3, width: "auto" }}>
            <CreatableSelect
              isClearable
              options={categories}
              value={category}
              onChange={handleChange}
              onCreateOption={(inputValue) => {
                const newCategory = {
                  name: inputValue,
                };
                createCategory(newCategory);
              }}
            />
          </FormControl>
          <Button variant="contained" sx={{ mt: 3 }} onClick={saveBtn}>
            Add note
          </Button>
        </Box>
      </Modal>

      <Modal
        open={openDeleteCategoryModal}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box id="editModal" sx={{ color: "black" }}>
          <FormControl sx={{ mt: 3, width: "auto" }}>
            <CreatableSelect
              isClearable
              options={categories}
              value={category}
              onChange={handleChange}
            />
          </FormControl>
          <Button variant="contained" sx={{ mt: 3 }} onClick={deleteCategory}>
            Delete category
          </Button>
        </Box>
      </Modal>
      <Snackbar
        open={errorAlert.open}
        autoHideDuration={6000}
        onClose={() => setErrorAlert({ ...errorAlert, open: false })}
      >
        <Alert
          onClose={() => setErrorAlert({ ...errorAlert, open: false })}
          severity="error"
        >
          {errorAlert.message}
        </Alert>
      </Snackbar>
    </>
  );
}
