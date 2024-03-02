import React, { useState } from "react";
import axios from "axios";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import Modal from "@mui/material/Modal";
import Chip from "@mui/material/Chip";
import { Button, IconButton } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ArchiveIcon from "@mui/icons-material/Archive";
import EditIcon from "@mui/icons-material/Edit";

export default function Note({ note, deleteNotes }) {
  const apiURL = "http://localhost:8080/api/v1/note/";
  const [open, setOpen] = useState(false);
  const [title, setTitle] = useState(note.title);
  const [description, setDescription] = useState(note.description);
  const [status, setStatus] = useState(note.status);
  const [archived, setArchived] = useState(note.archived);

  // DELETE
  const onDelete = async (uuid) => {
    await axios.delete(apiURL + uuid).then(function (response) {
      //Updates local notes
      deleteNotes(response.data);
    });
  };

  const handleDelete = () => {
    if (window.confirm("Delete this note?")) {
      onDelete(note.uuid);
    }
  };

  // Update
  const handleUpdate = () => {
    setOpen(true);
  };

  const onUpdate = async (uuid, updatedNote) => {
    await axios.put(`${apiURL}${uuid}`, updatedNote);
    location.reload();
  };

  const handleClose = () => {
    setOpen(false);
    setTitle(note.title);
    setDescription(note.description);
  };

  const saveUpdate = () => {
    const updatedNote = {
      title: title,
      description: description,
    };
    onUpdate(note.uuid, updatedNote);
    handleClose();
  };

  // Status

  const onStatus = async (uuid, status) => {
    await axios.patch(`${apiURL}${uuid}`, { status });
  };

  const handleStatus = async () => {
    if (window.confirm("Mark this note as completed?")) {
      const updatedStatus = !status;
      await onStatus(note.uuid, updatedStatus);
      setStatus(updatedStatus);
    }
  };

  // Archive

  const onArchive = async (uuid, archived) => {
    await axios.patch(`${apiURL}${uuid}`, { archived });
  };

  const handleArchive = async () => {
    if (window.confirm("Archive this note?")) {
      const updatedArchived = !note.archived;
      await onArchive(note.uuid, updatedArchived);
      setArchived(updatedArchived);
    }
  };

  return (
    <div>
      <div className="note">
        <div className="noteContent">
          <div className="note-info">
            <span className="note-title">{note.title}</span>
            <hr className="noteDivider" />
            <span className="note-description">{note.description}</span>
          </div>
          <div className="note-footer">
            <div className="note-footer-info">
              <Chip
                label={archived ? "Archived" : "Unarchived"}
                color={archived ? "warning" : "primary"}
              />
              <Chip
                label={status ? "Completed" : "Pending"}
                color={status ? "success" : "primary"}
              />
            </div>
            <div className="note-footer-cat">
              <Chip label={note.category.name} />
            </div>
            <div className="note-btns">
              <IconButton color="primary" onClick={handleDelete}>
                <DeleteIcon />
              </IconButton>
              <IconButton color="primary" onClick={handleUpdate}>
                <EditIcon />
              </IconButton>
              <IconButton color="primary" onClick={handleArchive}>
                <ArchiveIcon />
              </IconButton>
              <IconButton color="primary" onClick={handleStatus}>
                <CheckCircleIcon />
              </IconButton>
            </div>
          </div>
        </div>
      </div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box id="editModal">
          <TextField
            id="outlined-basic"
            label="Note title"
            variant="outlined"
            sx={{ mr: 3, mb: 4 }}
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <TextField
            id="outlined-basic"
            label="Note description"
            variant="outlined"
            sx={{ mr: 3, mb: 4 }}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          <Button onClick={saveUpdate} variant="outlined">
            Update
          </Button>
        </Box>
      </Modal>
    </div>
  );
}
