package com.example.tasktrifleappnative

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TasksDatabseHelper(context: Context) : SQLiteOpenHelper(context, DATABBASE_NAME, null, DATABBASE_VERSION){

    companion object{
        private const val DATABBASE_NAME = "tasks.db"
        private const val DATABBASE_VERSION = 1
        private const val TABLE_NAME = "alltasks"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DUEDATE = "dueDate"
        private const val COLUMN_PRIORITY = "priority"
        private const val COLUMN_STATUS = "status"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_CATEGORY TEXT, $COLUMN_DUEDATE TEXT, $COLUMN_PRIORITY TEXT, $COLUMN_STATUS TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertTask(task: Task){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_DESCRIPTION, task.description)
            put(COLUMN_CATEGORY, task.category)
            put(COLUMN_DUEDATE, task.dueDate)
            put(COLUMN_PRIORITY, task.priority)
            put(COLUMN_STATUS, task.status)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllTasks(): List<Task> {
        val tasksList = mutableListOf<Task>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
            val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
            val dueDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DUEDATE))
            val priority = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY))
            val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))

            val task = Task(id,title, description, category, dueDate, priority, status)
            tasksList.add(task)
        }
        cursor.close()
        db.close()
        return tasksList
    }

    fun updateTask(task: Task){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, task.title)
            put(COLUMN_DESCRIPTION, task.description)
            put(COLUMN_CATEGORY, task.category)
            put(COLUMN_PRIORITY, task.priority)
            put(COLUMN_STATUS, task.status)
            put(COLUMN_DUEDATE, task.dueDate)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(task.id.toString())
        db.update(TABLE_NAME,values,whereClause,whereArgs)
        db.close()
    }

    fun getTaskById(taskId: Int): Task{
        val db = writableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $taskId"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))
        val category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))
        val dueDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DUEDATE))
        val priority = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY))
        val status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS))

        cursor.close()
        db.close()
        return Task(id,title, description, category, dueDate, priority, status)
    }

    fun deleteTask(taskId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(taskId.toString())
        db.delete(TABLE_NAME,whereClause,whereArgs)
        db.close()
    }

}