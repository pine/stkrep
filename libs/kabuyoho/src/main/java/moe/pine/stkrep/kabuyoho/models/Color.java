package moe.pine.stkrep.kabuyoho.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
    BLACK("black"),
    RED("red"),
    GREEN("green"),
    ;

    private final String id;
}
