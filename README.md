Conclusion:
1. Syntax is awful to read for beginner. However, when you have the knowledge about Kotlin's quirks it'll sudenly all make sense. Still, ugly though.
2. Use ```val stateName = remember {mutableStateOf(what?)}``` for beginner. If you want to increase *ugh...* productivity then use ```var stateName by remember {mutableStateOf(what?)}``` .
3. Use ```var stateName by rememberSaveable {mutableStateOf(what?)}``` if you want that state to be remembered in case of screen orientation, theme changing, or other mildly-destructive actions.
4. Use ```val XXX by animateXXXAsState(if (CONDITION) XXX else YYY)``` to define a simple animation. That ```XXX``` variable will continuously updated according to the animation formulae by the Compose itself hence the 'animation' came from.
5. Use ```animationSpec``` parameter inside ```animateXXXAsState``` to configure the animation even further.

TL;DR: The syntax is fucking ugly and difficult to follow because everything is a lambda now. Crazy how the world went from declaring UI programatically in imperative style, then declaring UI using XML or other markup, only to come back declaring the UI programatically again. We need to trust our ancestor a little bit more.
