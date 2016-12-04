package pt.isel.pdm.profslist

import android.app.ListActivity
import android.app.LoaderManager
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleCursorAdapter
import pt.isel.pdm.profslist.provider.ProfsContract

class MainActivity : ListActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private val PROFS_LOADER = 0

    val adapter by lazy {
        val from = arrayOf(ProfsContract.Profs.NAME, ProfsContract.Profs.EMAIL)
        val to   = intArrayOf(android.R.id.text1, android.R.id.text2)

        SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, from, to, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listView.adapter = adapter

        loaderManager.initLoader(PROFS_LOADER, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?) =
            when (id) {
                PROFS_LOADER -> CursorLoader(
                        this,
                        ProfsContract.Profs.CONTENT_URI,
                        ProfsContract.Profs.SELECT_ALL,
                        null,
                        null,
                        null
                )
                else -> {
                    Log.w("Profs", "Unknown id for loader")
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
