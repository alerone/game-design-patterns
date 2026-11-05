package emberdeep.ejercicios.c9;

/**
 * ShotEvent
 */
public sealed interface ShotEvent permits Hit, Miss { }

record Hit(int damage) implements ShotEvent {}

record Miss() implements ShotEvent {}
