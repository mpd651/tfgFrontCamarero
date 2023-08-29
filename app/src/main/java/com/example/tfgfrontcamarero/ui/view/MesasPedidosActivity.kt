package com.example.tfgfrontcamarero.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.PedidoViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MesasPedidosActivity : AppCompatActivity() {

    private lateinit var usuario:Usuario
    private val pedidoViewModel: PedidoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesas_pedidos)

        usuario = intent.getSerializableExtra("usuario")as Usuario
        findViewById<TextView>(R.id.txtUserNameActivityDoble).setText("Usuario: "+usuario.nombre)
        var adapter = TabAdapter(supportFragmentManager)
        findViewById<ViewPager>(R.id.viewPager).adapter= adapter
        findViewById<TabLayout>(R.id.tabLayout).setupWithViewPager(findViewById<ViewPager>(R.id.viewPager))


        findViewById<TabLayout>(R.id.tabLayout).addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 1) {
                    pedidoViewModel.getPedidosActivos()
                    pedidoViewModel.pedidosModelDto.observe(this@MesasPedidosActivity, Observer {
                        val pedidosFragment = supportFragmentManager.findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + 1) as? PedidosFragment
                        pedidosFragment?.updatePedidoList(it)
                    })
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        });
    }



    inner class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> MesasFragment.newInstance(usuario)
                1 -> PedidosFragment.newInstance(usuario)
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Mesas"
                1 -> "Pedidos"
                else -> null
            }
        }
    }

    override fun onBackPressed()
    {
        val intent = Intent(this@MesasPedidosActivity, LoginActivity::class.java )
        this@MesasPedidosActivity.finish()
        startActivity(intent)
    }



}