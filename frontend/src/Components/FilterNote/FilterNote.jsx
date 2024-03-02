import React from "react";
import "./FilterNote.css";

export default function FilterNote(props) {
  const onFilterValueChanged = (e) => {
    console.log(e.target.value);
    props.filterValueSelected(e.target.value);
  };

  return (
    <div className="container">
      <h3>Filter notes</h3>
      <div className="filter-area">
        <select name="filterOption" onChange={onFilterValueChanged}>
          <option value="all">All</option>
          <option value="archived">Archived</option>
          <option value="unarchived">Unarchived</option>
          <option value="completed">Completed</option>
          <option value="pending">Pending</option>
        </select>
      </div>
    </div>
  );
}
