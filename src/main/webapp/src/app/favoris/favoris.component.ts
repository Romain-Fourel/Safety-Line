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
    MovieService.isConnected=true;
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
