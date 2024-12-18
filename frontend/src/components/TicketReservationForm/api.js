export const ticketReservationAPI = async (values, flightId, id) => {
  console.log(JSON.stringify(values));
  const url = `http://localhost:8080/user/passengers?flightId=${flightId}&userId=${id}`;

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(values),
    });
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
  } catch (error) {
    console.error(error.message);
  }
};

export const bookFlightAPI = async (
  flightId,
  id,
  seatClass,
  numberOfTickets
) => {
  const json = {
    userId: id,
    flightId: flightId,
    seatClass: seatClass,
    reservedSeats: numberOfTickets,
  };
  console.log(json);
  const url = `http://localhost:8080/user/reservations`;
  const request = new Request(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(json),
  });
  const requestCloned = request.clone();
  try {
    const response = await fetch(requestCloned);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
  } catch (error) {
    console.error(error.message);
  }
};
