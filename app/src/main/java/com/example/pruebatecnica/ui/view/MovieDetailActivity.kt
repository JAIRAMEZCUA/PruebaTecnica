package com.example.pruebatecnica.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pruebatecnica.BASE_URL_IMG
import com.example.pruebatecnica.MOVIE_KEY
import com.example.pruebatecnica.R
import com.example.pruebatecnica.data.model.Movie
import com.example.pruebatecnica.databinding.ActivityMovieDetailBinding
import com.example.pruebatecnica.domain.GetFavMovieUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity @Inject constructor(private val getMoviesUseCaseFav: GetFavMovieUseCase) :
    AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent?.extras?.getParcelable<Movie>(MOVIE_KEY)
        if (movie == null) {
            Toast.makeText(
                this,
                getString(R.string.error_showing_movie_not_found),
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        binding.back.setOnClickListener {
            onBackPressed()
        }
        /*
                La pantalla de detalles deberá mostrar el póster, título, resumen de la película, y la
                calificación de ésta. Contará con un botón que te permitirá guardar la película en tu lista
                local, o de estar guardada previamente permitirá eliminarla de la lista.*/
        binding.imageView.load(BASE_URL_IMG + movie.posterPath)
        binding.textViewTitle.text = getString(R.string.movie_title_format, movie.title)
        binding.textViewDescription.text = getString(R.string.movie_resumen_format, movie.overview)
        binding.textViewRating.text = getString(R.string.movie_rating_format, movie.voteAverage)
        binding.imgFav.setOnClickListener {
            showAlert(movie)

        }
    }

    fun showAlert(movie: Movie) {
        // Crear un AlertDialog.Builder
        val alertDialogBuilder = AlertDialog.Builder(this)

        // Configurar el título y el mensaje del AlertDialog
        alertDialogBuilder.setTitle(binding.textViewTitle.text)
        alertDialogBuilder.setMessage("Te gusta la pelicula")

        // Agregar un botón "Aceptar" al AlertDialog
        alertDialogBuilder.setPositiveButton("Si") { dialog, which ->
            // Código a ejecutar cuando se hace clic en el botón "Aceptar"
            lifecycleScope.launch {
                getMoviesUseCaseFav.invokeInsertFav(movie, true)
            }
        }

        // Agregar un botón "Cancelar" al AlertDialog
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            lifecycleScope.launch {
                getMoviesUseCaseFav.invokeInsertFav(movie, false)
            }
        }

        alertDialogBuilder.setCancelable(false)
        // Crear y mostrar el AlertDialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MoviesMenuActivity::class.java)
        startActivity(intent)
    }
}