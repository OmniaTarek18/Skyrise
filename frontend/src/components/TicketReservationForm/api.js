export const onSubmit = async (values, actions) => {
  console.log(values);
  const url = `http://localhost:8080/user/passengers?flightId=${1}&userId=${1}`;
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
    const json = await response.json();
    actions.setStatus({ status: "success", data: json });
    console.log(json);
  } catch (error) {
    console.error(error.message);
  }
  actions.setStatus("fail");
};
