package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    private final RestTemplate restTemplate;
    private final TrelloConfig trelloConfig;



    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl("https://api.trello.com/1/boards/60771d23132e81860a03d295?fields=name,id&key=24804656126ac3d26346dcd23deb7a93&=&token=06509a1c58471ae43018086e0b292d7c6127e8c1a867e98442ef72e8501ba00f&lists=all")
//                (trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();


//        try {
//            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//            return Optional.ofNullable(boardsResponse)
//                    .map(Arrays::asList)
//                    .orElse(Collections.emptyList())
//                    .stream()
//                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
//                    .filter(p -> p.getName().contains("Kodilla"))
//                    .collect(Collectors.toList());
//        } catch (RestClientException e) {
//            LOGGER.error(e.getMessage(), e);
//            return Collections.emptyList();
//        }
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

//    }


        public CreatedTrelloCard createdNewCard(TrelloCardDto trelloCardDto) {
            URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                    .queryParam("key", trelloConfig.getTrelloAppKey())
                    .queryParam("token", trelloConfig.getTrelloToken())
                    .queryParam("name", trelloCardDto.getName())
                    .queryParam("desc", trelloCardDto.getDescription())
                    .queryParam("pos", trelloCardDto.getPos())
                    .queryParam("idList", trelloCardDto.getListId())
                    .build()
                    .encode()
                    .toUri();

            return restTemplate.postForObject(url,null, CreatedTrelloCard.class);
    }
//    private URI boardUrl(){
//        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
//                .queryParam("key", trelloConfig.getTrelloAppKey())
//                .queryParam("token", trelloConfig.getTrelloToken())
//                .queryParam("fields", "name,id")
//                .queryParam("lists","all")
//                .build()
//                .encode()
//                .toUri();
//    }

}