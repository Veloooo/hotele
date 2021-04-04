package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloFacadeTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> dtos = new ArrayList<>();
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("1", "List", true));
        dtos.add(new TrelloBoardDto("1", "Board", lists));

        //When
        List<TrelloBoard> testBoard = trelloMapper.mapToBoards(dtos);

        //Then
        assertEquals(dtos.get(0).getName(), testBoard.get(0).getName());
        assertEquals(dtos.get(0).getId(), testBoard.get(0).getId());
        assertEquals(dtos.get(0).getLists().size(), testBoard.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> boards = new ArrayList<>();
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1", "List", true));
        boards.add(new TrelloBoard("1", "Board", lists));

        //When
        List<TrelloBoardDto> testBoard = trelloMapper.mapToBoardsDto(boards);

        //Then
        assertEquals(boards.get(0).getName(), testBoard.get(0).getName());
        assertEquals(boards.get(0).getId(), testBoard.get(0).getId());
        assertEquals(boards.get(0).getLists().size(), testBoard.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("1", "List", true));

        //When
        List<TrelloList> testList = trelloMapper.mapToList(lists);

        //Then
        assertEquals(lists.get(0).getName(), testList.get(0).getName());
        assertEquals(lists.get(0).getId(), testList.get(0).getId());
        assertEquals(lists.get(0).isClosed(), testList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList("1", "List", true));

        //When
        List<TrelloListDto> testList = trelloMapper.mapToListDto(lists);

        //Then
        assertEquals(lists.get(0).getName(), testList.get(0).getName());
        assertEquals(lists.get(0).getId(), testList.get(0).getId());
        assertEquals(lists.get(0).isClosed(), testList.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto card = new TrelloCardDto("card", "description", "top", "1");

        //When
        TrelloCard testCard = trelloMapper.mapToCard(card);

        //Then
        assertEquals(card.getName(), testCard.getName());
        assertEquals(card.getDescription(), testCard.getDescription());
        assertEquals(card.getPos(), testCard.getPos());
        assertEquals(card.getListId(), testCard.getListId());

    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card = new TrelloCard("card", "description", "top", "1");

        //When
        TrelloCardDto testCard = trelloMapper.mapToCardDto(card);

        //Then
        assertEquals(card.getName(), testCard.getName());
        assertEquals(card.getDescription(), testCard.getDescription());
        assertEquals(card.getPos(), testCard.getPos());
        assertEquals(card.getListId(), testCard.getListId());

    }
}
