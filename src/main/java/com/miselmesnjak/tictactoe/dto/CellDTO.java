package com.miselmesnjak.tictactoe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Misel on 5.2.2017
 */
@Data
@AllArgsConstructor
public class CellDTO {
    private int row;
    private int column;
    private String value;
}
