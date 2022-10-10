package com.example.prodan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.petList
import com.example.prodan.adapter
import com.example.prodan.data.pets
import com.example.prodan.databinding.FragmentHomeBinding
import java.util.Locale.filter
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    // Aquí van las variables de los filtros
    private var especie: String? = null
    private var sexo: Int? = null
    private var raza: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí tambien van las variables de los filtros
        arguments?.let {
            especie = it.getString("especie")
            sexo = it.getInt("sexo")
            raza = it.getString("raza")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageViewOptions.setOnClickListener {
            //Navegación por ID
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_optionsFragment)
        }

        // NAvegación a los filtros
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_filterFragment2)
        }

        // Variables no mutables de los filtros
        var _petList = petList
        var _especie = ""
        var _sexo = 0
        var _raza = ""

        especie?.let { _especie = it }
        sexo?.let { _sexo = it }
        raza?.let { _raza = it }

        // Creando instancia de adaptador

        val adapter = adapter(requireActivity(), _petList){
            val bundle = Bundle()
            bundle.putParcelable("pets",it)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
        }

        // Esto en necesario para que la primera vez que se abre la app no se apliquen filtros
        // Esto solo se activará si hay algún filtro, en caso de que se agrequen mas hay que ponerlos en las variables
        if (_especie != "" || _sexo != 0 || _raza != ""){

            _petList = filter(_especie,_sexo, _raza, _petList) as MutableList<pets>

            adapter.filterList(_petList)
        }

        binding.rvpets.adapter = adapter

        binding.rvpets.layoutManager =
            GridLayoutManager(requireActivity(),
                2, RecyclerView.VERTICAL, false)
    }


    private fun filter(especie:String, sexo:Int, raza:String, lista:List<pets> ): List<pets> {

        // Se crea una instancia de la lista inicial
        var listafiltrada  = lista

        // Por cada objeto si no cumple con las condiciones de los filtros se va quitando de la lista
        for (item in lista){

            // Especie
            if (especie == "Otro"){
                if (item.especie == "Perro" || item.especie == "Gato"){
                    listafiltrada = listafiltrada.minus(item)
                }
            }
            else if (especie != "" && item.especie != especie){
                listafiltrada = listafiltrada.minus(item)
            }

            // Sexo
            if (sexo != 0 && item.sexo != sexo){
                listafiltrada = listafiltrada.minus(item)
            }

            if (raza != "" && item.raza != raza){
                listafiltrada = listafiltrada.minus(item)
            }

        }
        return listafiltrada
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}