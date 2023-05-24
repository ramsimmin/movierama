package com.example.movierama.controller;

import com.example.movierama.dto.MovieDto;
import com.example.movierama.enums.SortOptionEnum;
import com.example.movierama.service.MovieService;
import com.example.movierama.service.MovieVoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieVoteService movieVoteService;

    private static final int defaultCurrentPage = 1;
    private static final int defaultPageSize = 5;
    private static final String defaultSortDirection = "DESC";
    private static final String defaultSortField = "created_at";


    @RequestMapping(value = {"/", "/movies"})
    public String showDashboard(Model model,
                                @RequestParam(value = "email", required = false) String searchUser,
                                @RequestParam(value = "userName", required = false) String searchUserName,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam(value = "sortOption", required = false) String sortOption) {

        Page<MovieDto> moviePage = getMoviePage(page, searchUser, sortOption);
        addModelAttributes(model, moviePage, page.orElse(defaultCurrentPage), searchUser, searchUserName, sortOption);
        return "movies";
    }


    @GetMapping("/movie/create")
    public String showCreateMovieForm(Model model) {
        MovieDto movie = new MovieDto();
        model.addAttribute("movie", movie);
        return "createmovie";
    }

    @PostMapping("/movie/save")
    public String createMovie(@Valid @ModelAttribute("movie") MovieDto movieDto,
                              BindingResult result,
                              Model model) {

        if (result.hasErrors()) {
            model.addAttribute("movie", movieDto);
            return "createmovie";
        } else {
            movieService.saveMovie(movieDto);
            return "redirect:/movie/create?success";
        }
    }

    @RequestMapping(value = {"/movies/vote"})
    public String vote(Model model,
                       @RequestParam(value = "movie_id") String movieId,
                       @RequestParam(value = "vote") Boolean vote,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam(value = "email", required = false) String searchUser,
                       @RequestParam(value = "searchUserName", required = false) String searchUserName,
                       @RequestParam(value = "sortOption", required = false) String sortOption) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            movieVoteService.saveMovieVote(movieId, auth.getName(), vote);
        }

        Page<MovieDto> moviePage = getMoviePage(page, searchUser, sortOption);
        addModelAttributes(model, moviePage, page.orElse(defaultCurrentPage), searchUser, searchUserName, sortOption);
        return "movies";

    }


    @RequestMapping(value = {"/movies/vote/delete"})
    public String cancelVote(Model model,
                             @RequestParam(value = "movie_id") String movieId,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam(value = "email", required = false) String searchUser,
                             @RequestParam(value = "searchUserName", required = false) String searchUserName,
                             @RequestParam(value = "sortOption", required = false) String sortOption) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            movieVoteService.deleteMovieVote(movieId, auth.getName());
        }

        Page<MovieDto> moviePage = getMoviePage(page, searchUser, sortOption);
        addModelAttributes(model, moviePage, page.orElse(defaultCurrentPage), searchUser, searchUserName, sortOption);
        return "movies";

    }


    private static void addModelAttributes(Model model, Page<MovieDto> moviePage, int currentPage, String searchUser, String searchUserName, String sortOption) {
        int totalPages = moviePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchUser", searchUser);
        model.addAttribute("searchUserName", searchUserName);
        model.addAttribute("sortOption", sortOption);
    }


    private Page<MovieDto> getMoviePage(Optional<Integer> page, String searchUser, String sortOption) {
        int currentPage = page.orElse(defaultCurrentPage);
        String sortField = defaultSortField;
        String sortDirection = defaultSortDirection;

        if (!StringUtils.isBlank(sortOption)) {
            sortField = SortOptionEnum.valueOf(sortOption).getSortField();
            sortDirection = SortOptionEnum.valueOf(sortOption).getSortDirection();
        }

        Page<MovieDto> moviePage;
        PageRequest pageRequest = PageRequest.of(currentPage - 1, defaultPageSize, Sort.Direction.valueOf(sortDirection), sortField);

        if (!StringUtils.isBlank(searchUser)) {
            moviePage = movieService.findByEmailPaginated(searchUser, pageRequest);
        } else {
            moviePage = movieService.findPaginated(pageRequest);
        }

        return moviePage;
    }


}
