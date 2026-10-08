# Practica 1 PSP
## Context

Context del Problema
Una empresa de ciberseguretat rep diàriament grans volums de fitxers de registre (logs) des de múltiples servidors. Per evitar saturar la memòria RAM de l'aplicació principal i aprofitar la potència del sistema operatiu, es requereix dissenyar un sistema multiprocés en Java: un programa central (AnalitzadorPrincipal) que actua com a orquestrador (pare) i delega el filtrat i comptatge a processos independents (FiltreLog) mitjançant redirecció de fluxos i canonades (IPC).
