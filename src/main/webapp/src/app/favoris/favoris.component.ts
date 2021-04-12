import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-favoris',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.scss']
})
export class FavorisComponent implements OnInit {

  title:string;

  favoriteMovies:any[];

  constructor(private movieService:MovieService) { 
    this.title="My Favorite Movies !";
  }

  ngOnInit() {
    this.favoriteMovies = this.movieService.movies;
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
