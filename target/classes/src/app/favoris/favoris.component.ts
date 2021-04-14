import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-favoris',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.scss']
})
export class FavorisComponent implements OnInit {

  favoriteMovies:any[];

  constructor(private movieService:MovieService) { 
  }

  ngOnInit() {
    this.favoriteMovies = this.movieService.movies;

    //TODO: replace by the id of the user currently connected
    MovieService.idUser=0;
  }

  getFavoriteMovies(){
    var favoriteMovies = [];
    for (const movie of this.movieService.movies) {
      if (movie!==undefined && movie.isFavorite){
        favoriteMovies.push(movie);
      }
    }
    return favoriteMovies;
  }


}
