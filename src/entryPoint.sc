require: slotfilling/slotFilling.sc
  module = sys.zb-common
  
require: js/getters.js
require: js/reply.js
require: js/actions.js
  
require: sc/roll_dice.sc
require: sc/restart.sc

patterns:
    $AnyText = $nonEmptyGarbage

theme: /
    state: Start
        # При запуске приложения с кнопки прилетит сообщение /start.
        q!: $regex</start>
        # При запуске приложения с голоса прилетит сказанная фраза.
        q!: (запусти | открой | вруби) Змейки и лестницы
        if: get_request($context).payload.character.name === 'Джой'
            a: Привет, добро пожаловать в игру змейки и лестницы. Чтобы узнать, как в нее играть, скажи Правила
        else:
            a: Здравствуйте, добро пожаловать в игру змейки и лестницы. Чтобы узнать правила игры, скажите Правила
        script: addSuggestions(['Правила'], $context)
        
    state: Привет
        q!: (Привет|Здравствуй)
        if: get_request($context).payload.character.name === 'Джой'
            a: Привет, как дела?
        else:
            a: Здравствуйте!
    
    state: Help
        q!: (Что ты умеешь|Помощь|Подсказка|Правила)
        if: get_request($context).payload.character.name === 'Джой'
            a: Цель игры - дойти до конца поля. При попадании на нижнюю часть лестницы, ты поднимаешься по ней, но при попадании на голову змеи, ты опускаешься к ее хвосту. Чтобы подбросить кубик, скажи "Кинь кубик" или просто "Кубик". Чтобы начать игру заново, скажи "Заново".
        else:
            a: Цель игры - дойти до конца поля. При попадании на нижнюю часть лестницы, вы поднимаетесь по ней, но при попадании на голову змеи, вы опускаетесь к ее хвосту. Чтобы подбросить кубик, скажите "Кинь кубик" или просто "Кубик". Чтобы начать игру заново, скажите "Заново".
            
    state: Fallback
        event!: noMatch
        a: Я не понимаю
        script: addSuggestions(['Правила'], $context)

