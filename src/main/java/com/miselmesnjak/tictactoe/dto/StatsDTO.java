package com.miselmesnjak.tictactoe.dto;

import com.miselmesnjak.tictactoe.domain.Player;
import lombok.Data;

import java.util.List;

/**
 * Created by Misel on 5.2.2017.
 */
@Data
public class StatsDTO {
    private List<Player> stats;
}
