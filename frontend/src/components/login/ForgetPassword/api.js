export const changePasswordAPI = async (values, actions) => {
  console.log(values); // Log form values to the console
  console.log(actions); // Log actions (such as resetForm) for debugging purposes
  const url = `http://localhost:8080/password/reset?email=${values.email}`;
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: ""
    });
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