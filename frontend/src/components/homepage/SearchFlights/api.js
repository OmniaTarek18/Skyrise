export const getCountriesAndAirportsToTravelAPI = async () => {
  const url = "http://localhost:8080/airports";
  const request = new Request(url);
  try {
    const response = await fetch(request);
    if (!response.ok) {
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    console.log(json);
    return json;
  } catch (error) {
    console.error(error.message);
  }
};
