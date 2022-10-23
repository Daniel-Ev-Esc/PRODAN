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
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.Attributes
import com.example.prodan.data.Data
import com.example.prodan.data.Pet
import com.example.prodan.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var datas: Pet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aquí tambien van las variables de los filtros
        arguments?.let {
            especie = it.getString("especie")
            sexo = it.getInt("sexo")
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
        // Variables no mutables de los filtros
        var _especie = ""
        var _sexo = 0

        especie?.let { _especie = it }
        sexo?.let { _sexo = it }

        showPets(_especie,_sexo)

        binding.imageViewOptions.setOnClickListener {
            //Navegación por ID
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_optionsFragment)
        }

        binding.imageViewWelcome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_welcomeFragment)
        }

        // NAvegación a los filtros
        binding.floatingActionButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_filterFragment2)
        }
    }

    fun showPets(especie:String, sexo:Int) {
        val retrofit = RetroFit.getInstance().create(ApiPets:: class.java)

        retrofit.getAllPets().enqueue(object : Callback<Pet> {
            override fun onResponse(
                call: Call<Pet>?, response: Response<Pet>?
            ) {
                datas = response?.body()
                Log.i("Taggg", datas.toString())

                // Creando instancia de adaptador
                val adapter = datas?.let { it ->
                    adapter(requireActivity(), it.data){
                        val bundle = Bundle()
                        val pack = Attributes(it.attributes.age,"",it.attributes.description,null,it.attributes.male,it.attributes.name,"","","",it.attributes.image?.data?.attributes?.url.toString())
                        bundle.putParcelable("pets",pack)
                        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
                    }
                }

                // Esto en necesario para que la primera vez que se abre la app no se apliquen filtros
                // Esto solo se activará si hay algún filtro, en caso de que se agrequen mas hay que ponerlos en las variables

                var petList = datas?.data

                Log.i("lista", datas?.data.toString())

                if (especie != "" || sexo != 0){

                    petList = filter(especie,sexo, datas)

                }

                if (petList != null) {
                    Log.i("lista","Filtrando...")
                    adapter?.filterList(petList)
                }

                binding.rvpets.adapter = adapter
                Log.i("objetos", adapter?.data.toString())
                binding.rvpets.layoutManager =
                    GridLayoutManager(requireActivity(),
                        2, RecyclerView.VERTICAL, false)
                val controller = AnimationUtils.loadLayoutAnimation(requireActivity(), R.anim.layout_animation)
                binding.rvpets.layoutAnimation = controller
            }



            override fun onFailure(call: Call<Pet>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }

    private fun filter(especie:String, sexo:Int, lista: Pet?): List<Data>? {

        // Se crea una instancia de la lista inicial
        var listafiltrada  = lista?.data

        // Por cada objeto si no cumple con las condiciones de los filtros se va quitando de la lista
        for (item in lista?.data!!) {
            Log.i("objetos",item.toString())

            // Especie
            if (especie == "Otro") {
                if (item.attributes.type == "perro" || item.attributes.type == "gato") {
                    listafiltrada = listafiltrada?.minus(item)
                }
            } else if (especie != "" && item.attributes.type != especie) {
                listafiltrada = listafiltrada?.minus(item)
            }

            // Sexo
            if (sexo != 0 && (!item.attributes.male || item.attributes.male)) {
                if (item.attributes.male && sexo != 1 || !item.attributes.male && sexo != 2){
                    listafiltrada = listafiltrada?.minus(item)
                }
            }

        }
        Log.i("objetos",listafiltrada.toString())
        return listafiltrada
    }
}