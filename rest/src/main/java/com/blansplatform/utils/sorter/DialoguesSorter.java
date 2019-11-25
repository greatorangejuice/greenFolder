/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.sorter;

import com.blansplatform.dto.MessageDto;

import java.util.*;

public class DialoguesSorter {
    public static Map<String, List<MessageDto>> messagesSortForGroups(List<MessageDto> messages) {
        DialoguesSorter.messageCollectionSortByUsers(messages);
        Map<String, List<MessageDto>> dialoguesMap = new HashMap<>();
        String currentUserFrom = messages.get(0).getUserFrom();
        String currentUserTo = messages.get(0).getUserTo();
        List<MessageDto> messagesDto = new LinkedList<>();
        for (int i = 0; i <= messages.size() - 1 ; i++) {
            String nextUserFrom = messages.get(i).getUserFrom();
            String nextUserTo = messages.get(i).getUserTo();
            if ((nextUserFrom.equals(currentUserFrom) && nextUserTo.equals(currentUserTo))
                    || nextUserFrom.equals(currentUserTo) && nextUserTo.equals(currentUserFrom)) {
                messagesDto.add(messages.get(i));
            } else {
                DialoguesSorter.dialogSortByTime(messagesDto);
                dialoguesMap.put(currentUserFrom + " " + currentUserTo, messagesDto);
                messagesDto = new LinkedList<>();
                currentUserFrom = messages.get(i).getUserFrom();
                currentUserTo = messages.get(i).getUserTo();
                i--;
            }
        }
        dialoguesMap.put(currentUserFrom + " " + currentUserTo, messagesDto);
        return dialoguesMap;
    }

    private static void messageCollectionSortByUsers(List<MessageDto> messages){
        Comparator<MessageDto> comparator = (m1, m2) -> {
            Long hashM1 = (long) (m1.getUserFrom().hashCode() + m1.getUserTo().hashCode());
            Long hashM2 = (long) (m2.getUserFrom().hashCode() + m2.getUserTo().hashCode());
            return hashM1.compareTo(hashM2);
        };
        messages.sort(comparator);
    }

    private static void dialogSortByTime(List<MessageDto> messages){
        Comparator<MessageDto> comparator = Comparator.comparing(MessageDto::getDate);
        messages.sort(comparator);
    }
}
