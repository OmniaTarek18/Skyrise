import React from "react";
import {
  Elements,
} from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import CheckoutPage from "./CheckoutPage";
import "./style.css"
const stripePromise = loadStripe(
  "pk_test_51Qc4xi4Sp68VTRAk1CRFncBd1NLf1In9HUKDXQyNqvM9SBy5TYF9hyqRH06zkVq1PwK3APdvyESy5ZVAIAfOb2wd00dZzUIRkc"
);
export const convertToSubcurrency = (amount, factor = 100) => {
  return Math.round(amount * factor);
};

function PaymentForm({price}) {
  return (
    <div className="payment-container">
      <h2 style={{marginBottom:"1rem"}}>Total Price: {price}$</h2>
      <Elements
        stripe={stripePromise}
        options={{
          mode: "payment",
          amount: convertToSubcurrency(price),
          currency: "usd",
        }}
      >
        <CheckoutPage amount={price} />
      </Elements>
    </div>
  );
}

export default PaymentForm;
