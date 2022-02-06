package com.laioffer.jupiter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.response.Game;
import com.laioffer.jupiter.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

//    @Autowired
//    private GameService gameService;


    //  /game?game_name=war%20xxx
    //  /game
    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public void getGame(@RequestParam(value = "game_name", required = false) String gameName, HttpServletResponse response) throws IOException, ServletException {
        //@RequestParam作用：把key game_name做为requestParam的值，然后把它的值自动赋值到String gameName
        // required = false 表示可以搜索任意游戏
        response.setContentType("application/json;charset=UTF-8");
        // Return the dedicated game information if gameName is provided in the request URL, otherwise return the top x games
        if (gameName != null) {
            Game game = gameService.searchGame(gameName);
            response.getWriter().print(new ObjectMapper().writeValueAsString(game));
        } else {
            List<Game> gameList = gameService.topGames((0));
            response.getWriter().print(new ObjectMapper().writeValueAsString(gameList));

        }
    }


}
