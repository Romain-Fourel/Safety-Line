import { Component, OnInit } from '@angular/core';
import { MovieService } from '../services/movie.service';

@Component({
  selector: 'app-movie-view',
  templateUrl: './movie-view.component.html',
  styleUrls: ['./movie-view.component.scss']
})
export class MovieViewComponent implements OnInit {

  movies:any[];

  selectedMovie:any=null;

  input:string="";

  constructor(private movieService:MovieService){
  }

  ngOnInit(){
    this.movies = this.movieService.movies;
    MovieService.isConnected=true;
  }

  /**
   * returns all movies which are not deleted, that means all movies not undefined
   */
  getMovies(){
    var moviesNotDeleted:any[] = [];
      for (const movie of this.movieService.movies) {
        if (movie!==undefined){
          moviesNotDeleted.push(movie);
        }
      }
      return moviesNotDeleted;
  }

  getSearchedMovies(){
    return MovieService.searchedMovies;
  }

  onAddMovie(){
    var chosenMovie:any;
    for (const movie of this.getSearchedMovies()) {
      if (movie.Title === this.input){
        chosenMovie = movie;
      }
    }

    if(this.input!==""){
      this.movieService.addMovie(chosenMovie,false);
      this.input = "";
    }
  }

  onKey(){
    this.movieService.getMoviesFromOMDb(this.input);
  }

}
