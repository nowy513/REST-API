package com.crud.tasks.trello.mapper;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    public void testMapToBoards(){
//        Given
        TrelloListDto trelloListDto = new TrelloListDto("12412", "TrelloListDto", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("2414", "TrelloBoardDto", trelloListDtos);
        List<TrelloBoardDto> trelloBoardDtos= new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

//        When
        List<TrelloBoard> trelloBoardsMapper = trelloMapper.mapToBoards(trelloBoardDtos);
//
//        Then
        assertEquals(1, trelloBoardsMapper.size());
    }

    @Test
    public void testMapToBoardsDto(){
//
        TrelloList trelloList = new TrelloList("124", "trelloList", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("1423", "trelloBoard", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

//        When
        List<TrelloBoardDto> trelloBoardsDtoMapper = trelloMapper.mapToBoardsDto(trelloBoards);
//        Then
        assertEquals(1, trelloBoardsDtoMapper.size());
    }

    @Test
    public void testMapToList(){
//        Given
        TrelloListDto trelloListDto = new TrelloListDto("1231", "trelloListDto", false);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);

//        When
        List<TrelloList> trelloListsMapper = trelloMapper.mapToList(trelloListDtos);

//        Then
        assertEquals(1, trelloListsMapper.size());
    }

    @Test
    public void testMapToListDto(){
//        Given
        TrelloList trelloList = new TrelloList("1313", "trelloList", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

//        When
        List<TrelloListDto> trelloListDToMapper = trelloMapper.mapToListDto(trelloLists);

//        Then
        assertEquals(1, trelloListDToMapper.size());
    }

    @Test
    public void testMapToCardDto(){
//        Given
        TrelloCard trelloCard = new TrelloCard("TrelloCardTest", "This is Test", "412", "25");

//        When
        TrelloCardDto trelloCardDtoMapper = trelloMapper.mapToCardDto(trelloCard);

//        Then
        assertEquals("TrelloCardTest", trelloCardDtoMapper.getName());
    }

    @Test
    public void testMapToCard(){
//        Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TrelloCardDto", "This is Test", "222","49295");

//        When
        TrelloCard trelloCardMapper = trelloMapper.mapToCard(trelloCardDto);

//        Then
        assertEquals("TrelloCardDto", trelloCardMapper.getName());
    }

}
