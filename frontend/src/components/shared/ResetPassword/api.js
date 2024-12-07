export const resetPasswordAPI = async (values, actions) => {
  console.log(values); // Log form values to the console
  const url = `http://localhost:8080/change?email=${values.email}&newPassword=${values.newPassword}`;

  const request = await fetch(url, {
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