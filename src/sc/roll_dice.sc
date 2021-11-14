theme: /

    state: Подкинуть кубик
        q!: [Подкинуть|Подкинь|Кинь|Кинуть|Бросить|Подбрось|Подбросить|Брось] (Кубик|Кость)
        if: checkWon($context)
            script: 
                $session.randomInt = Math.floor(Math.random() * 6 + 1)
                rollDice($session.randomInt, $context)
            a: Выпало {{ $session.randomInt }}
            #if: willWin($session.randomInt, $context)
            #    a: Это победа! 
        