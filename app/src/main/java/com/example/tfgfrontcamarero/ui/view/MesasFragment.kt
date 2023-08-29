package com.example.tfgfrontcamarero.ui.view

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.MesaViewModel
import com.google.android.flexbox.FlexboxLayout

class MesasFragment : Fragment(){
    private lateinit var flexContainer: FlexboxLayout
    private val mesaViewModel: MesaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.mesas_fragment_layout, container, false)
        flexContainer = rootView.findViewById(R.id.idFlexLayout)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityContext: Context = requireActivity()

        var usuario = (arguments?.getSerializable(USUARIO) as? Usuario)!!

        mesaViewModel.getAllMesas()
        mesaViewModel.mesasModel.observe(viewLifecycleOwner, Observer {
            for (mesa in it) {
                val button = Button(activityContext)
                val buttonSize = resources.displayMetrics.widthPixels/3

                val params = ActionBar.LayoutParams(buttonSize, buttonSize)
                button.layoutParams = params
                button.setTag(mesa.id)
                button.setText(mesa.numeroMesa)
                flexContainer.addView(button)

                button.setOnClickListener{
                    val intent = Intent(activityContext, CategoriaProductoActivity::class.java )
                    intent.putExtra("mesa", mesa)
                    intent.putExtra("usuario", usuario)
                    startActivity(intent)
               }
            }
        })

    }

    companion object {
        private const val USUARIO = "usuario"

        fun newInstance(usuario: Usuario): MesasFragment {
            val fragment = MesasFragment()
            val args = Bundle()
            args.putSerializable(USUARIO, usuario)
            fragment.arguments = args
            return fragment
        }
    }
}



