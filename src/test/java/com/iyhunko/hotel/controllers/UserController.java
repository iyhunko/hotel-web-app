package com.iyhunko.hotel.controllers;

import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;

public class UserController {

//    @Test
//    public void whenFirstPageOfResourcesAreRetrieved_thenSecondPageIsNext(){
//        Response response = RestAssured.get("/users?page=0&size=2");
//
//        String uriToNextPage = extractURIByRel(response.getHeader("Link"), "next");
//        assertEquals("/users?page=1&size=2", uriToNextPage);
//    }
//    @Test
//    public void whenFirstPageOfResourcesAreRetrieved_thenNoPreviousPage(){
//        Response response = RestAssured.get("/users?page=0&size=2");
//
//        String uriToPrevPage = extractURIByRel(response.getHeader("Link"), "prev");
//        assertNull(uriToPrevPage );
//    }
//    @Test
//    public void whenSecondPageOfResourcesAreRetrieved_thenFirstPageIsPrevious(){
//        Response response = RestAssured.get("/users?page=1&size=2");
//
//        String uriToPrevPage = extractURIByRel(response.getHeader("Link"), "prev");
//        assertEquals("/users?page=0&size=2", uriToPrevPage);
//    }
//    @Test
//    public void whenLastPageOfResourcesIsRetrieved_thenNoNextPageIsDiscoverable(){
//        Response first = RestAssured.get("/users?page=0&size=2");
//        String uriToLastPage = extractURIByRel(first.getHeader("Link"), "last");
//
//        Response response = RestAssured.get(uriToLastPage);
//
//        String uriToNextPage = extractURIByRel(response.getHeader("Link"), "next");
//        assertNull(uriToNextPage);
//    }
//
//    public static String extractURIByRel(final String linkHeader, final String rel) {
//        if (linkHeader == null) {
//            return null;
//        }
//
//        String uriWithSpecifiedRel = null;
//        final String[] links = linkHeader.split(", ");
//        String linkRelation = null;
//        for (final String link : links) {
//            final int positionOfSeparator = link.indexOf(';');
//            linkRelation = link.substring(positionOfSeparator + 1, link.length()).trim();
//            if (extractTypeOfRelation(linkRelation).equals(rel)) {
//                uriWithSpecifiedRel = link.substring(1, positionOfSeparator - 1);
//                break;
//            }
//        }
//
//        return uriWithSpecifiedRel;
//    }
//
//    static Object extractTypeOfRelation(final String linkRelation) {
//        final int positionOfEquals = linkRelation.indexOf('=');
//        return linkRelation.substring(positionOfEquals + 2, linkRelation.length() - 1).trim();
//    }
}
