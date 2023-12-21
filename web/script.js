function add_to_cart(pid, pname, pprice) {
    let cart = localStorage.getItem("cart");

    if (cart === null) {
        let products = [];
        let product = {
            productId: pid,
            productName: pname,
            productQuantity: 1,
            productPrice: pprice
        };

        products.push(product);
        localStorage.setItem("cart", JSON.stringify(products));
       // console.log("Product is added for the first time");
        showToast("Product is added to cart")
    } else {
        let pcart = JSON.parse(cart);

        let oldProduct = pcart.find((item) => item.productId == pid);

        if (oldProduct) {
            // we have to increase the quantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1;

            pcart.map((item) => {
                if (item.productId == oldProduct.productId) {
                    item.productQuantity = oldProduct.productQuantity;
                }
            });

            localStorage.setItem("cart", JSON.stringify(pcart));
            console.log("Product quantity is increased");
             showToast(oldProduct.productName + " quantity is increased,, Quantity= "+oldProduct.productQuantity)
        } else {
            // we have to add the product
            let product = {
                productId: pid,
                productName: pname,
                productQuantity: 1,
                productPrice: pprice
            };

            pcart.push(product);
            localStorage.setItem("cart", JSON.stringify(pcart));
            console.log("Product is added");
             showToast("Product is added to cart")
        }
    }
    updateCart();
}

// Update Cart

function updateCart() {
    
 let cartString = localStorage.getItem("cart");
 let cart = JSON.parse(cartString);
 
 if(cart==null || cart.length==0) {
     console.log("Cart is empty")
     $(".cart-items").html("( 0 )");
     $(".cart-body").html("<h3>Cart does not have any products </h3>");
     $(".checkout-btn").attr('disabled',true);
 }else{
     
     console.log(cart);
     
      $(".cart-items").html(`( ${cart.length} )`);
      
      let table=`
        <table class='table'>  
        <thead class='thead-light'>
            <tr>
                <th> Item Name </th>
                 <th> Price </th>
                  <th> Quantity </th>
                   <th> Total Price </th>
                   <th> Increase </th>
                   <th> Decrease </th>
                   <th> Action </th>
                
            
            </tr>
            
            
        </thead>
`;
        let totalPrice=0;
        cart.map((item)=> {
            
            table+=`
            
            <tr>
                <td> ${item.productName} </td>
                 <td> ${item.productPrice} </td>
                  <td> ${item.productQuantity} </td>
                   <td> ${item.productQuantity * item.productPrice} </td>
                     <td> <button onclick="increaseQuantity(${item.productId})" class="btn btn-success btn-sm">
                            <i class="fa fa-plus"></i> 
                        </button> </td>

                       <td> <button onclick="decreaseQuantity(${item.productId})" class="btn btn-warning btn-sm">
                            <i class="fa fa-minus"></i>
                        </button> </td>

                   <td> <button onclick='deleteItemFromCart(${item.productId})' class='btn btn-danger btn-sm'>Remove</button> </td>
            </tr>
`       
            totalPrice+=item.productPrice * item.productQuantity;
            
        })
        
        table=table+`
      <tr> <td colspan='5' class='text-right font-weight-bold m-5'> Total Price: ${totalPrice} </td> </tr>
</table>`
         $(".cart-body").html(table);
        $(".checkout-btn").attr('disabled',false);
 }
}


function deleteItemFromCart(pid) {
  let cart = JSON.parse(localStorage.getItem('cart'));
  
  let newcart = cart.filter((item) => item.productId != pid)
  
  localStorage.setItem('cart', JSON.stringify(newcart))
  
  updateCart();
  
  showToast("Product is removed from cart")
}


// Function to increase the quantity of a product in the cart
function increaseQuantity(pid) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let product = cart.find((item) => item.productId == pid);

    if (product) {
        product.productQuantity += 1;
        localStorage.setItem('cart', JSON.stringify(cart));
        updateCart();
        showToast(`${product.productName} quantity increased, Quantity = ${product.productQuantity}`);
    }
}

// Function to decrease the quantity of a product in the cart
function decreaseQuantity(pid) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    let product = cart.find((item) => item.productId == pid);

    if (product && product.productQuantity > 1) {
        product.productQuantity -= 1;
        localStorage.setItem('cart', JSON.stringify(cart));
        updateCart();
        showToast(`${product.productName} quantity decreased, Quantity = ${product.productQuantity}`);
    } else if (product && product.productQuantity === 1) {
        // If quantity is 1, remove the item from the cart
        deleteItemFromCart(pid);
    }
}


$(document).ready(function (){
    updateCart();
})


function showToast(content) {
    $("#toast").addClass("display");
    $("#toast").html(content);
    setTimeout(() => {
        $("#toast").removeClass("display");
    }, 2000);
}


function goToCheckout(){
    window.location="checkout.jsp"
}