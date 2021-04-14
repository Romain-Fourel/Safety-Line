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

    //TODO: replace by the id of the user currently connected
    MovieService.idUser=0;
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
    var chosenMovie:any=undefined;
    for (const movie of this.getSearchedMovies()) {
      if (movie.Title === this.input){
        chosenMovie = movie;
      }
    }

    if(chosenMovie!==undefined){
      this.movieService.addMovie(chosenMovie);
      this.input = "";
    }
    else{
      alert("Please choose a movie which is in the database !");
    }
  }

  onKey(){
    this.movieService.getMoviesFromOMDb(this.input);
  }

}
