export const changePasswordAPI = async (values, actions) => {
  console.log(values);

  const url = `http://localhost:8080/validate?accountId=${values.id}&password=${values.password}`;

  const request = new Request(url, {
    method: "POST",
  });
  const requestCloned = request.clone();

  try {
    const response = await fetch(requestCloned);
    if (!response.ok) {
      actions.setStatus("fail");
      throw new Error(`Response status: ${response.status}`);
    }
    // const json = await response.json();
    actions.setStatus("success");
    // console.log(json);
  } catch (error) {
    actions.setStatus("fail");
    console.error(error.message);
  }
};