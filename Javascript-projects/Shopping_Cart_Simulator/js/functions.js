$(document).ready(function() {

    let rowCount = 0;
    let itemNames = [];
    let infoOfRows = [];
    let totalTransactionCost = 0;

    //adding default items
    addItemRow("Potato", 0.5, "img/potatoes.jpg");
    addItemRow("Corn", 0.75, "img/corn.jpg");
    updateTotals()

    $("#addToCart").on("click", addNewItem);

    function addItemRow(itemName, cost, image) {
        rowCount++;
        $("#itemTable").append(`<tr id="item${rowCount}Row"></tr>`); // adds the item to the table
        $(`#item${rowCount}Row`).append(`<td id="infoColumn${rowCount}" class="infoColumn"></td>`); //creates information column

        $(`#infoColumn${rowCount}`).append(`<p id="item${rowCount}Info">${itemName} <button type="button" name="${itemName} ${rowCount}" id="remove${rowCount}"> X </button> </p> `); // adds name to info column
        $(`#infoColumn${rowCount}`).append(`<img src="${image}" alt="${itemName} picture"/>`); // adds picture to info column

        $(`#item${rowCount}Row`).append(`<td class="costColumn"><label id="unit${rowCount}Cost">$${cost}</label></td>`); // adds item cost per unit
        $(`#item${rowCount}Row`).append(`<td class="quanityColumn"><input type="text" id="item${rowCount}Quanity" value="1" maxlength="5"></td>`); //adds quantity field
        $(`#item${rowCount}Row`).append(`<td class="costColumn"><label id="total${rowCount}Cost">${cost}</label></td>`); // adds cost of all units of an item
        infoOfRows.push(`#item${rowCount}Quanity #total${rowCount}Cost ` + cost);
        itemNames.push(itemName);

        $(`#remove${rowCount}`).on("click", removeItem);
        $(`#item${rowCount}Quanity`).on("change", updateTotals);
    }


    function removeItem() { //removes the item from the cart
        let parts = this.name.split(" "); //parts[0] is the items name, parts[1] is the row number.

        for (let i = 0; i < itemNames.length; i++) {
            if (itemNames[i] === parts[0]) {
                console.log(itemNames[i] === parts[0])
                infoOfRows.splice(i, 1);
                itemNames.splice(i, 1);
                break;
            }
        }

        $(`#item${parts[1]}Row`).remove();
        updateTotals();
    }

    function updateTotals() { // updates the transaction price, and total price per product
        totalTransactionCost = 0;

        for (let i = 0; i < infoOfRows.length; i++) {
            rowInfo = infoOfRows[i].split(" ");
            cost = Number(rowInfo[2]) * Number($(rowInfo[0]).val()) * 1.08;
            if (cost >= 0) {
                totalTransactionCost += cost;
                $(rowInfo[1]).html("$" + cost.toFixed(2));
            }
            else {
                $(rowInfo[0]).val(1);
                $(rowInfo[1]).html("$" + rowInfo[2]);
                totalTransactionCost+=Number(rowInfo[2]);
            }
        }

        $("#totalCost").html("Total Transaction Cost: $" + totalTransactionCost.toFixed(2));
    }

    function addNewItem() { //lets the user add a new item
        currentItemName = $("#addItemName").val().toLowerCase();
        let alreadyUsed = false;

        for (let i = 0; i < itemNames.length; i++) {
            if (currentItemName === itemNames[i].toLowerCase()) {
                alreadyUsed = true;
                break;
            }
        }

        if (alreadyUsed) {
            alert(currentItemName + " is already in the cart.");
            return;
        }

        if (currentItemName && $("#addItemCost").val() && $("#addItemImg").val()) {
            addItemRow($("#addItemName").val(), $("#addItemCost").val(), $("#addItemImg").val());
            updateTotals();
            $("#name").css("color", "black");
            $("#cost").css("color", "black");
            $("#img").css("color", "black");

            $("#addItemName").val("");
            $("#addItemCost").val("");
            $("#addItemImg").val("");
        }
        else {
            if (!$("#addItemName").val()) {
                $("#name").css("color", "red");
            }
            else {
                $("#name").css("color", "black");
            }

            if (!$("#addItemCost").val()) {
                $("#cost").css("color", "red");
            }
            else {
                $("#cost").css("color", "black");
            }

            if (!$("#addItemImg").val()) {
                $("#img").css("color", "red");
            }
            else {
                $("#img").css("color", "black");
            }
        }
    }

});
