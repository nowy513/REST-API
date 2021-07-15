package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TrelloValidatorTest {

    @InjectMocks
    TrelloValidator trelloValidator;

    @Test
    void testValidateTrelloBoards(){
//        Given
        TrelloList trelloList = new TrelloList("243", "ListName", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("45", "BoardName", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);

//        When
        List<TrelloBoard> validatorTrello = trelloValidator.validateTrelloBoards(trelloBoards);

//        Then
        assertEquals(1, validatorTrello.size());
    }

}
