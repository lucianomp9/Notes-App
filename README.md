# 📝 Notes SPA

---
## Index
- [Backend Technologies](#backend-technologies)
- [Frontend Technologies](#frontend-technologies)
- [ER Model](#er-model)
- [Functionalities](#functionalities)
	- [Create Note](#create-note)
	- [Note Status/Archive](#note-statusarchive)
	- [Filter Notes](#filter-notes)
	- [Delete Notes](#delete-notes)

---
#### Clone the repository:
```git clone https://github.com/lucianomp9/Notes-App```

## Backend Technologies
#### Java, Spring Boot, Hibernate, Design patterns (MVC, DTO's, Dependency Inyection), Exception handling.

## Frontend Technologies 
#### ReactJS, Vite
##### Libraries like Axios, Material-UI, React-Select were used 

 
 
 ## Database configuration
    spring.datasource.username= your-username

    spring.datasource.password= your-password

    spring.datasource.url=jdbc:mysql://localhost/notesappdb?useSSL=false&serverTimeZone=UTC

 ## Create the Database
  
  Open your MySQL client and create the database using the creation script provided at: src/main/resources/dbScript.sql

  Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
    
  Run the Application

  Once you have configured the database and saved the changes in `application.properties`, you can run the application. 
  
  Find the main class `NotesApp` (annotated with `@SpringBootApplication`) and click the run button in your development environment.


  Open the terminal in the project directory and run `npm install` to install dependencies.
  
  Run the frontend application with the command `npm run dev`.

## ER Model
The Entity-Relationship model corresponding to the database.

![ER Model](https://github.com/lucianomp9/Notes-App/assets/86586819/9debad80-9420-4ca8-8df9-f73cd5b9bf20)



# Functionalities

## Create Note
![create-note](https://github.com/lucianomp9/Notes-App/assets/86586819/3ab30ff8-5095-456d-a6ac-d0d07082e924)

## Note Status/Archive
![change-status](https://github.com/lucianomp9/Notes-App/assets/86586819/4ccc1f4f-4a8c-4b91-9af0-b297cdc42f96)

## Filter Notes
![filter-notes](https://github.com/lucianomp9/Notes-App/assets/86586819/78300003-035c-4a03-b5a8-57f1a060f5d4)

## Delete Notes
![delete-notes](https://github.com/lucianomp9/Notes-App/assets/86586819/ae385922-18e2-458b-ac95-79aa804b24a4)




> [!NOTE]
> If a category wants to be deleted, while is being asinged for a Note, an alert will show off, saying 'Error deleting category. There are notes with this category. Please delete them before removing the category'



---
> TODO:
> Allow multiple categories for a note

