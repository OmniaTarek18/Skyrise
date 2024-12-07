// onSubmit function to handle form submission
export const onSubmit = async (values, actions) => {
  console.log(values);

  const url = "http://localhost:8080/signUp/customer";
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
      actions.setStatus("fail");
      throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    actions.setStatus("success");
    console.log(json);
  } catch (error) {
    actions.setStatus("fail");
    console.error(error.message);
  }
};
