package com.example.prodan

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.prodan.data.Attributes
import com.example.prodan.data.Pet
import com.example.prodan.databinding.FragmentWelcomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var privacyPolicy = false
    private var especie: String? = null
    private var sexo: Int? = null

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private var datas: Pet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
        if(privacyPolicy == false) {
            val mBuilder = AlertDialog.Builder(activity)
                .setTitle("AVISO DE PRIVACIDAD")
                .setMessage(
                    "Prodefensa Animal A.C., con domicilio en Plutarco Elías Calles 307, Tampiquito, 66240 San Pedro Garza García, N.L., es el responsable del uso y protección de sus datos personales, y al respecto le informamos lo siguiente:\n" +
                            "\n" +
                            "¿Para qué fines utilizaremos sus datos personales?\n\n" +
                            "Los datos personales que recabamos de usted, los utilizaremos para las siguientes finalidades que son necesarias para el servicio que solicita:\n" +
                            "\n" +
                            "Respuesta a mensajes del formulario de contacto\n" +
                            "\n" +
                            "¿Dónde puedo consultar el aviso de privacidad integral?\n\n" +
                            "Para conocer mayor información sobre los términos y condiciones en que serán tratados sus datos personales, como los terceros con quienes compartimos su información personal y la forma en que podrá ejercer sus derechos ARCO, puede consultar el aviso de privacidad integral con una petición vía correo electrónico:\n" +
                            "\n" +
                            "informes@prodan.org.mx\n\n" +
                            "Última actualización de este aviso de privacidad: 29/09/2022\n"
                )
                .setPositiveButton("ACEPTAR") { dialog, which ->
                    saveData()
                }
                .setCancelable(false)

            val mAlertDialog = mBuilder.create()
            mAlertDialog.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var _especie = ""
        var _sexo = 0

        especie?.let { _especie = it }
        sexo?.let { _sexo = it }

        showPets(_especie,_sexo)

        binding.imageViewOptions2.setOnClickListener {
            //Navegación por ID
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_optionsFragment)
        }

        binding.imageView8.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_homeFragment)
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
                        findNavController().navigate(R.id.action_welcomeFragment_to_detailsFragment,bundle)
                    }
                }

                // Esto en necesario para que la primera vez que se abre la app no se apliquen filtros
                // Esto solo se activará si hay algún filtro, en caso de que se agrequen mas hay que ponerlos en las variables


                binding.rvWelcome.adapter = adapter
                binding.rvWelcome.set3DItem(true)
                binding.rvWelcome.setAlpha(true)
                binding.rvWelcome.setInfinite(true)
            }



            override fun onFailure(call: Call<Pet>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })

    }

    private fun saveData() {

        val acceptedPolicy = true

        val sharedPreferences = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("BOOL_KEY", acceptedPolicy)
        }.apply()
    }

    private fun loadData() {
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val savedBool = sharedPreferences.getBoolean("BOOL_KEY", null == true)

        privacyPolicy = savedBool

    }


}