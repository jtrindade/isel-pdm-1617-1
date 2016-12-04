package pt.isel.pdm.profslist.provider

import android.provider.BaseColumns

/*
 * Original ideas taken from:
 * https://www.grokkingandroid.com/android-tutorial-writing-your-own-content-provider/
 */

object DbSchema {

    val DB_NAME = "profs.db"
    val DB_VERSION = 1

    val COL_ID = BaseColumns._ID

    object Profs {
        val TBL_NAME = "profs"

        val COL_CODE = "code"
        val COL_NAME = "name"
        val COL_EMAIL = "email"

        val DDL_CREATE_TABLE =
                "CREATE TABLE " + TBL_NAME + "(" +
                        COL_ID + " INTEGER PRIMARY KEY, " +
                        COL_CODE + " INTEGER UNIQUE, " +
                        COL_NAME + " TEXT, " +
                        COL_EMAIL + " TEXT UNIQUE)"

        val DDL_DROP_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME
    }

}