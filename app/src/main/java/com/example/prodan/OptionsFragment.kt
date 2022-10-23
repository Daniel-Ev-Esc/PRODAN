package com.example.prodan

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.prodan.databinding.FragmentOptionsBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOptionsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonFB.setOnClickListener {
            openFacebook()
        }
        binding.buttonIG.setOnClickListener {
            openInstagram()
        }

        binding.buttonTW.setOnClickListener {
            openTW()
        }

        binding.buttonYT.setOnClickListener {
            openYT()
        }

        binding.buttonM.setOnClickListener {
            val mBuilder = AlertDialog.Builder(activity)
                .setTitle("Correo Electrónico de PRODAN")
                .setMessage("informes@prodan.org.mx")
            val mAlertDialog = mBuilder.create()
            mAlertDialog.show()
        }

        binding.buttonAV.setOnClickListener {
            val mBuilder = AlertDialog.Builder(activity)
                .setTitle("AVISO DE PRIVACIDAD")
                .setMessage("Prodefensa Animal A.C., con domicilio en Plutarco Elías Calles 307, Tampiquito, 66240 San Pedro Garza García, N.L., es el responsable del uso y protección de sus datos personales, y al respecto le informamos lo siguiente:\n" +
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
                        "Última actualización de este aviso de privacidad: 29/09/2022\n")
                .setPositiveButton("ACEPTAR"){dialog, which ->

                }
            val mAlertDialog = mBuilder.create()
            mAlertDialog.show()
        }

        binding.buttonBack.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.action_optionsFragment_to_homeFragment)
            Navigation.findNavController(view).navigate(R.id.action_optionsFragment_to_homeFragment)
        }

    }

    private fun openTW() {
        val uri = Uri.parse("https://twitter.com/prodanmty")
        val i = Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.twitter.android");
        try {
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Log.e("LOGLOG", "Application not intalled.")
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/prodanmty")
                )
            )
        }
    }

    private fun openYT() {
        val uri = Uri.parse("https://www.youtube.com/user/PRODANMTY")
        val i = Intent(Intent.ACTION_VIEW, uri);
        i.setPackage("com.google.android.youtube");
        try {
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Log.e("LOGLOG", "Application not intalled.")
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/user/PRODANMTY")
                )
            )
        }
    }

    private fun openFacebook() {
        val facebookId = "fb://page/267399839949060"
        val urlPage = "https://www.facebook.com/ProdanAC"
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)))
        } catch (e: Exception) {
            Log.e("LOGLOG", "Application not intalled.")
            //Open url web page.
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)))
        }
    }
    private fun openInstagram() {
        val uri = Uri.parse("https://www.instagram.com/prodanmty/")
        val i = Intent(Intent.ACTION_VIEW, uri)
        i.setPackage("com.instagram.android")

        try {
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Log.e("LOGLOG", "Application not intalled.")
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/prodanmty/")
                )
            )
        }
    }
}