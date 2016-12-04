package pt.isel.pdm.contacts

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.SimpleCursorAdapter

class MainActivity : ListActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private val CONTACTS_LOADER = 0

    val adapter by lazy {
        val from = arrayOf(ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.LOOKUP_KEY)
        val to   = intArrayOf(android.R.id.text1, android.R.id.text2)

        SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, from, to, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.adapter = adapter

        loaderManager.initLoader(CONTACTS_LOADER, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?) =
        when (id) {
            CONTACTS_LOADER -> CursorLoader(
                    this,
                    ContactsContract.Contacts.CONTENT_URI,
                    arrayOf(
                        ContactsContract.Contacts._ID,
                        ContactsContract.Contacts.DISPLAY_NAME,
                        ContactsContract.Contacts.LOOKUP_KEY
                    ),
                    ContactsContract.Contacts.IN_VISIBLE_GROUP + "=1",
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME + " ASC"
                )
            else -> {
                Log.w("Contacts", "Unknown id for loader")
                throw IllegalArgumentException("id")
            }
        }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        adapter.changeCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        adapter.changeCursor(null)
    }
}
