package pt.isel.jhht.pdm.profs

import android.app.ListActivity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray

class MainActivity : ListActivity() {

    data class Prof(val number : Int, val name : String)

    class ProfViewHolder(val nameTextView : TextView, val numberTextView : TextView)

    private val TEACHERS_URI = "https://adeetc.thothapp.com/api/v1/teachers"

    private var theTeachers : Array<Prof>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        application.requestQueue.add(
            JsonObjectRequest(
                TEACHERS_URI,
                null,
                {
                    val jsonTeachers = it.get("teachers") as JSONArray

                    val teachers = jsonTeachers.iterator().asSequence().map {
                        Prof(it["number"] as Int, it["shortName"] as String)
                    }.toList().toTypedArray()

                    theTeachers = teachers

                    val itemLayoutId = android.R.layout.simple_list_item_2
                    val inflater = LayoutInflater.from(this)
                    val adapter = object : ArrayAdapter<Prof>(this, itemLayoutId, teachers) {
                        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                            val itemView = convertView ?:
                                    inflater.inflate(itemLayoutId, parent, false)
                                            .withTag({
                                                ProfViewHolder(
                                                        it.findViewById(android.R.id.text1) as TextView,
                                                        it.findViewById(android.R.id.text2) as TextView
                                                )
                                            })

                            val viewHolder = itemView.tag as ProfViewHolder

                            val prof = teachers[position]

                            viewHolder.nameTextView.text = prof.name
                            viewHolder.numberTextView.text = prof.number.toString()

                            viewHolder.nameTextView.setTextColor(
                                if (prof.name.contains("Jo"))
                                    Color.rgb(148, 0, 211)
                                else
                                    Color.BLACK
                            )

                            return itemView
                        }
                    }

                    listView.adapter = adapter
                },
                {
                    Log.d("Profs/Act", it.toString())
                    Toast.makeText(this, "Failed to get teachers", Toast.LENGTH_LONG).show()
                }
            )
        )

        listView.onItemClickListener =
            AdapterView.OnItemClickListener {
                parent, view, position, id ->
                    Toast.makeText(this, theTeachers!![position].name, Toast.LENGTH_LONG).show()
            }
    }
}

var totalTags = 0

fun View.withTag(tagFactory : (View) -> Any) : View {
    Log.d("Prof/Main", "Setting tag #${ ++totalTags }")
    this.tag = tagFactory(this)
    return this
}
