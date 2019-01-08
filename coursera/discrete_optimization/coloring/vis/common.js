/*
The MIT License (MIT)

Copyright (c) 2014 Jerry Gamble

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/


function roundNumber(number, digits) {
    let multiple = Math.pow(10, digits);

    return Math.round(number * multiple) / multiple;
}

//Returns specified number of unique colors
function makeColorGradient(len) {

    const frequency = 1.665;
    const center = 128;
    const width = 100;
    let colors = [];

    if (len === undefined) len = 50;

    for (let i = 0; i < len; ++i) {
        const red = Math.sin(frequency*i) * width + center;
        const grn = Math.sin(frequency*i + 2) * width + center;
        const blu = Math.sin(frequency*i + 4) * width + center;
        colors[i] = RGB2Color(red,grn,blu);
    }

    return colors;

}

function RGB2Color(r,g,b) {
    return '#' + byte2Hex(r) + byte2Hex(g) + byte2Hex(b);
}

function byte2Hex(n) {
    var nybHexString = "0123456789ABCDEF";
    return String(nybHexString.substr((n >> 4) & 0x0F,1)) + nybHexString.substr(n & 0x0F,1);
}

function dist(a,b) {
    return Math.sqrt(Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2));
}

function errorMessage(itemList, error, itemName, pluralItemName) {
    if (pluralItemName === undefined) {
        pluralItemName = itemName + "s";
    }
    if (itemList.length === 1) {
        return itemName + " " + itemList[0] + " " + error + ".";
    } else if (itemList.length > 1 && itemList.length < 4) {
        return pluralItemName + " " + itemList.slice(0,itemList.length-1).join(", ")
            + " and " + itemList[itemList.length-1] + " " + error + ".";
    } else if (itemList.length >= 4) {
        return pluralItemName + " " + itemList.slice(0,4).join(", ")
            + " and others " + error + ".";
    }
}

function constraintProportions(o, width, height) {
    const spanX = o['x_max'] - o['x_min'];
    const spanY = o['y_max'] - o['y_min'];
    if (height !== 0 && spanY !== 0) {
        const ratio = width / height;
        if (spanX / spanY > ratio) {
            const yMiddle = (o['y_min'] + o['y_max']) / 2;
            o['y_min'] = yMiddle - spanX / ratio / 2;
            o['y_max'] = yMiddle + spanX / ratio / 2;
        } else {
            const xMiddle = (o['x_min'] + o['x_max']) / 2;
            o['x_min'] = xMiddle - spanY * ratio / 2;
            o['x_max'] = xMiddle + spanY * ratio / 2;
        }
    }
}
