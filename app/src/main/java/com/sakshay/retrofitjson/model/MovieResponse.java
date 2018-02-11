package com.sakshay.retrofitjson.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aries on 02-02-2018.
 */

public class MovieResponse implements Serializable{

     @SerializedName("page")
        private int page;
        @SerializedName("results")
        private List<MovieResultData> results;
        @SerializedName("total_results")
        private int totalResults;
        @SerializedName("total_pages")
        private int totalPages;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<MovieResultData>  getResults() {
            return results;
        }

        public void setResults(List<MovieResultData> results) {
            this.results = results;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }
    }