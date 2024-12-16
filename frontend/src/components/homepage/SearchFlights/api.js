export const searchFlightsAPI = async (values, actions) => {
  console.log(values);
  const url = "http://localhost:8080/";
  const request = new Request(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(values),
  });
  const requestCloned = request.clone();

  try {
    const response = await fetch(requestCloned);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    actions.setStatus("success");
  } catch (error) {
    console.error(error.message);
  }
  actions.setStatus("fail");
};

export const getCountriesAndAirportsToTravelAPI = async (values, actions) => {
  console.log(values);
  const url = "http://localhost:8080/";
  const request = new Request(url);
  try {
    const response = await fetch(request);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    actions.setStatus("success");
  } catch (error) {
    console.error(error.message);
  }
  actions.setStatus("fail");
};
