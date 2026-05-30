radio.onReceivedNumber(function (receivedNumber) {
    // long press = smile
    // short press = shocked
    if (receivedNumber == 2) {
        if (x < 0) {
            x += 1
            ghost.showImage(x)
        }
    } else if (receivedNumber == 1) {
        basic.clearScreen()
        basic.showLeds(`
            # . . . #
            . . . . .
            # . . . #
            . # # # .
            . . . . .
            `)
        music.play(music.tonePlayable(262, music.beat(BeatFraction.Whole)), music.PlaybackMode.UntilDone)
        basic.clearScreen()
        ghost.showImage(x)
    } else if (receivedNumber == 0) {
        basic.clearScreen()
        basic.showLeds(`
            # . . . #
            . . . . .
            # # # # #
            # . . . #
            # # # # #
            `)
        music.play(music.tonePlayable(294, music.beat(BeatFraction.Quarter)), music.PlaybackMode.UntilDone)
        basic.clearScreen()
        ghost.showImage(x)
    }
})
input.onLogoEvent(TouchButtonEvent.LongPressed, function () {
    radio.sendNumber(1)
})
input.onLogoEvent(TouchButtonEvent.Pressed, function () {
    radio.sendNumber(0)
})
let ghost: Image = null
let x = 0
radio.setGroup(1)
basic.showIcon(IconNames.Ghost)
x = 0
ghost = images.iconImage(IconNames.Ghost)
basic.forever(function () {
    if (input.soundLevel() > 128) {
        // ghost will transfer to other microbit
        if (x > -5) {
            x += -1
            ghost.showImage(x)
            radio.sendNumber(2)
        }
    }
})
